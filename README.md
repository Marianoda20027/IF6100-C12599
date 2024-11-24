
Aquí tienes un documento detallado en formato Markdown, incluyendo todos los pasos, configuraciones y archivos requeridos, tal como solicitaste:

---

# Guía Completa de Configuración de Terraform en Oracle Cloud Infrastructure (OCI)

## Introducción a Terraform

Terraform de HashiCorp es una herramienta de **Infraestructura como Código (IaC)** que permite definir y gestionar recursos de infraestructura en la nube y locales mediante archivos de configuración legibles, reutilizables y versionables.

## Flujo de Trabajo de Terraform

Terraform sigue un flujo de trabajo básico en tres etapas:

1. **Write (Escribir):** Define los recursos en los archivos de configuración.
2. **Plan (Planificar):** Genera un plan de ejecución con los cambios propuestos en la infraestructura.
3. **Apply (Aplicar):** Ejecuta el plan aprobado, respetando las dependencias entre recursos y actualizando la infraestructura.

## Comandos 
Aquí tienes una lista de los comandos más importantes de Terraform y sus explicaciones:

1. **`terraform init`**  
   - Este comando inicializa un directorio de trabajo que contiene archivos de configuración de Terraform. 
   - Descarga e instala los proveedores necesarios definidos en la configuración y configura el entorno para empezar a trabajar con Terraform.
   - Solo se necesita ejecutar una vez al principio de un proyecto o después de realizar cambios importantes en la configuración de los proveedores.

2. **`terraform plan`**  
   - Genera y muestra un plan de ejecución de Terraform, detallando los cambios que se realizarían en la infraestructura para alinearla con la configuración.
   - Permite revisar los recursos que se crearán, modificarán o eliminarán antes de ejecutar los cambios. 
   - Es una buena práctica revisar el plan antes de aplicar cualquier cambio para evitar errores o modificaciones no deseadas.

3. **`terraform apply`**  
   - Aplica el plan de ejecución generado y crea o modifica la infraestructura para que coincida con la configuración definida en los archivos de Terraform.
   - Ejecuta todas las acciones necesarias (crear, actualizar o eliminar recursos) para alinear la infraestructura con el estado deseado.
   - Puede pedirse confirmación antes de aplicar los cambios o ejecutarse con `-auto-approve` para omitir esta confirmación.

4. **`terraform destroy`**  
   - Destruye todos los recursos creados por la configuración de Terraform.
   - Es útil para limpiar la infraestructura cuando ya no es necesaria o al finalizar una prueba para evitar costos adicionales.
   - Como en `apply`, puede pedirse confirmación antes de ejecutar el comando, o usarse con `-auto-approve` para aplicar directamente.

5. **`terraform validate`**  
   - Revisa los archivos de configuración de Terraform y verifica que la sintaxis y la estructura sean correctas.
   - No se conecta a ningún proveedor ni verifica que las credenciales sean correctas; solo realiza una validación de la sintaxis de los archivos.

6. **`terraform fmt`**  
   - Aplica un formato estandarizado al código de Terraform, ordenando y alineando el contenido de los archivos.
   - Ayuda a mantener el código organizado y consistente, especialmente cuando se trabaja en equipo.

7. **`terraform show`**  
   - Muestra el estado de la infraestructura, tal como lo representa el archivo de estado de Terraform.
   - Puede ser útil para revisar detalles específicos sobre los recursos actuales y sus configuraciones.

8. **`terraform state list`**  
   - Muestra una lista de todos los recursos que están siendo gestionados por Terraform en el archivo de estado.
   - Esto permite ver qué recursos se están rastreando actualmente y su relación con la configuración de Terraform.

9. **`terraform output`**  
   - Muestra los valores de las salidas definidas en el archivo de configuración de Terraform.
   - Es útil para obtener información de la infraestructura (como direcciones IP, nombres de recursos, etc.) después de aplicar los cambios.

10. **`terraform workspace`**  
    - Permite gestionar múltiples entornos (workspaces) dentro de un mismo proyecto de Terraform, como `dev`, `staging` y `production`.
    - Cada espacio de trabajo mantiene su propio archivo de estado, permitiendo crear y gestionar infraestructuras separadas dentro del mismo código base.

11. **`terraform import`**  
    - Importa recursos existentes en la infraestructura para que Terraform comience a gestionarlos.
    - Se usa cuando se tienen recursos creados manualmente o por otros medios y se desea incluirlos en el estado de Terraform sin recrearlos.

12. **`terraform graph`**  
    - Genera un gráfico de dependencias de los recursos gestionados por Terraform en formato DOT (Graphviz).
    - Es útil para visualizar las relaciones y dependencias entre los recursos definidos.

Estos comandos cubren las principales funcionalidades de Terraform y son esenciales para gestionar y mantener la infraestructura como código de manera efectiva.


## Guía Paso a Paso para Usar Terraform con OCI

### 1. Instalar Terraform

1. Descarga la última versión de Terraform desde el [sitio oficial](https://www.terraform.io/downloads.html).
2. Selecciona el paquete adecuado para tu sistema operativo.
3. Descomprime el archivo `.zip` y mueve el archivo `terraform` a un directorio en tu `PATH`.
4. Verifica la instalación ejecutando:

   ```bash
   terraform --version
   ```

### 2. Obtener las Credenciales de OCI

Para que Terraform se conecte a OCI, necesitas varias credenciales:

- **Tenancy OCID:** Navega a **Administración > Arrendamiento** en OCI y copia el OCID.
- **User OCID:** Dirígete a tu perfil en OCI y copia el User OCID.
- **Fingerprint y clave privada:**
  - Genera claves SSH si no tienes:

    ```bash
    mkdir -p ~/.oci
    openssl genrsa -out ~/.oci/oci_api_key.pem 2048
    openssl rsa -pubout -in ~/.oci/oci_api_key.pem -out ~/.oci/oci_api_key_public.pem
    ```

  - Sube la clave pública en la sección **Claves API** de tu usuario en OCI.
  - Obtén la huella digital (fingerprint) de la clave.

### 3. Configurar el Proveedor OCI en Terraform

Crea un archivo `provider.tf` para definir el proveedor de OCI en Terraform.

```hcl
provider "oci" {
  tenancy_ocid     = "<YOUR_TENANCY_OCID>"
  user_ocid        = "<YOUR_USER_OCID>"
  private_key_path = "~/.oci/oci_api_key.pem"
  fingerprint      = "<YOUR_FINGERPRINT>"
  region           = "<YOUR_REGION>"
}
```

### 4. Inicializar Terraform

```bash
terraform init
```

Este comando descarga e instala los proveedores necesarios.

### 5. Crear un Compartimento en OCI

```hcl
resource "oci_identity_compartment" "example_compartment" {
  name            = "lab-compartment"
  compartment_id  = "<YOUR_TENANCY_OCID>"
  description     = "Example compartment created with Terraform"
}
```

### 6. Escribir Configuración para Crear una Instancia de Compute

```hcl
resource "oci_core_instance" "oracle_instance" {
  availability_domain = data.oci_identity_availability_domains.ads.availability_domains[0].name
  compartment_id      = "<COMPARTMENT_OCID>"
  shape               = "VM.Standard3.Flex"
  
  shape_config {
    ocpus         = 1
    memory_in_gbs = 6
  }

  source_details {
    source_id   = "<IMAGE_OCID>"
    source_type = "image"
  }

  create_vnic_details {
    assign_public_ip = true
    subnet_id        = "<SUBNET_OCID>"
  }

  metadata = {
    ssh_authorized_keys = file("~/.ssh/terraform_example_key.pub")
  }
  
  preserve_boot_volume = false
}
```

### 7. Crear Claves de Cifrado SSH

```bash
ssh-keygen -t rsa -N "" -b 2048 -C "terraform-key" -f ~/.ssh/terraform_example_key
```

### 8. Crear una Red Virtual en la Nube (VCN) con un Módulo

```hcl
module "vcn" {
  source  = "oracle-terraform-modules/vcn/oci"
  version = "3.1.0"

  # Inputs
  compartment_id = "<COMPARTMENT_OCID>"
  region         = "<REGION>"

  vcn_name       = "vcn-module"
  vcn_dns_label  = "vcnmodule"
  vcn_cidrs      = ["10.0.0.0/16"]

  create_internet_gateway = true
  create_nat_gateway      = true
  create_service_gateway  = true
}
```

### 9. Crear una Lista de Seguridad para Subred Privada

Crea un archivo `private-security-list.tf`:

```hcl
resource "oci_core_security_list" "private_security_list" {
  compartment_id = "<COMPARTMENT_OCID>"
  vcn_id         = module.vcn.vcn_id

  display_name = "security-list-for-private-subnet"

  egress_security_rules {
    stateless        = false
    destination      = "0.0.0.0/0"
    destination_type = "CIDR_BLOCK"
    protocol         = "all"
  }
}
```

### 10. Crear una Lista de Seguridad para Subred Pública

```hcl
resource "oci_core_security_list" "public_security_list" {
  compartment_id = "<COMPARTMENT_OCID>"
  vcn_id         = module.vcn.vcn_id

  display_name = "security-list-for-public-subnet"

  egress_security_rules {
    stateless        = false
    destination      = "0.0.0.0/0"
    destination_type = "CIDR_BLOCK"
    protocol         = "all"
  }

  ingress_security_rules {
    stateless        = false
    source           = "0.0.0.0/0"
    source_type      = "CIDR_BLOCK"
    protocol         = "6"
    tcp_options {
      min = 22
      max = 22
    }
  }

  ingress_security_rules {
    stateless   = false
    source      = "0.0.0.0/0"
    source_type = "CIDR_BLOCK"
    protocol    = "1"
    icmp_options {
      type = 3
      code = 4
    }
  }
}
```
Aquí tienes un resumen breve de cada regla:

#### Egress (Salida)
1. **Regla de Salida:** Permite que cualquier tráfico salga hacia cualquier dirección IP en Internet sin restricciones en el tipo de protocolo.

#### Ingress (Entrada)
1. **SSH (Puerto 22):** Permite conexiones SSH desde cualquier dirección IP, esencial para la administración remota.
2. **ICMP Tipo 3, Código 4:** Permite recibir mensajes de error de red ICMP desde cualquier origen (útil para diagnosticar problemas de conectividad).
3. **ICMP Interno (Ping):** Permite mensajes ICMP desde direcciones IP internas del VCN, útil para verificar conectividad entre recursos dentro de la red.

### 11. Crear una Subred Privada

Crea un archivo `private-subnet.tf`:

```hcl
resource "oci_core_subnet" "vcn_private_subnet" {
  compartment_id    = "<COMPARTMENT_OCID>"
  vcn_id            = module.vcn.vcn_id
  cidr_block        = "10.0.1.0/24"
  route_table_id    = module.vcn.nat_route_id
  security_list_ids = [oci_core_security_list.private_security_list.id]

  display_name = "private-subnet"
}
```

### 12. Configurar Salidas en `outputs.tf`

```hcl
# Outputs for compute instance
output "instance_name" {
  value = oci_core_instance.oracle_instance.display_name
}
output "public_ip_for_compute_instance" {
  value = oci_core_instance.oracle_instance.public_ip
}

# Outputs for VCN
output "vcn_id" {
  description = "OCID of the VCN that is created"
  value       = module.vcn.vcn_id
}

# Outputs for Security Lists
output "private_security_list_name" {
  value = oci_core_security_list.private_security_list.display_name
}
output "public_security_list_name" {
  value = oci_core_security_list.public_security_list.display_name
}

# Outputs for Subnets
output "private_subnet_name" {
  value = oci_core_subnet.vcn_private_subnet.display_name
}
```

### 13. Ejecutar Terraform

1. **Inicializar:**

   ```bash
   terraform init
   ```

2. **Revisar el Plan de Implementación:**

   ```bash
   terraform plan
   ```

3. **Aplicar los Cambios:**

   ```bash
   terraform apply
   ```

   Confirma con `yes` para ejecutar los cambios.

4. **Verificar el Estado de los Recursos:**

   ```bash
   terraform show
   ```

5. **Destruir los Recursos (Opcional):**

   ```bash
   terraform destroy
   ```

---

