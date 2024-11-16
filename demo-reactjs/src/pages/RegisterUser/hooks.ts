import { useNavigate } from 'react-router-dom';
import { useApiHandler } from '../../hooks/useApiHandlers';
import useNotificationHandler from '../../hooks/useNotificationHandler';
import { RegisterUserRequest } from '../../models/users.models';
import { registerUser } from '../../services/users.service';
import { RegisterUserForm } from './types';

export const useDependencies = () => {
	const { handleMutation } = useApiHandler();
	const { setErrorNotificaiton } = useNotificationHandler();
	const navigate = useNavigate();
	const initialValues = {
		name: '',
		email: '',
		password: '',
	};

	const rules = {
		name: [
			{
				required: true,
				message: 'Por favor ingrese su nombre',
			},
		],
		email: [
			{
				required: true,
				message: 'Por favor ingrese su correo',
			},
		],
		password: [
			{
				required: true,
				message: 'Por favor ingrese su contraseña',
			},
		],
		passwordConfirmation: [
			{
				required: true,
				message: 'Por favor ingrese su contraseña',
			},
		],
	};

	const handleSubmit = async (parms: RegisterUserForm) => {
		//validar que las contraseñas sean iguales
		if (parms.password !== parms.passwordConfirmation) {
			return;
		}

		const request: RegisterUserRequest = {
			name: parms.name,
			email: parms.email,
			password: parms.password,
		};
		const { isError, message } = await handleMutation(registerUser, request);

		if (isError) {
			setErrorNotificaiton(message);
			return;
		} else {
			navigate('/Login');
		}
		console.log(`${parms.name} ${parms.email} ${parms.password}`);
	};

	return {
		handleSubmit,
		initialValues,
		rules,
	};
};


export default useNotificationHandler;