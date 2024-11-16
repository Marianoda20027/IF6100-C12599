import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App.tsx';
import RegisterUser from './pages/RegisterUser/index.tsx';
import Login from './pages/Login/Login.tsx';
import Application from './Application.tsx';
//import './index.css';

createRoot(document.getElementById('root')!).render(
	<StrictMode>
		<Application />
	</StrictMode>,
);


