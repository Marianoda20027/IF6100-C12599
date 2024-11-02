import React, { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import RegisterUser from './pages/RegisterUser';
import Login from './pages/Login/Login';
createRoot(document.getElementById('root')!).render(
	<StrictMode>
		<Login />
	</StrictMode>,
);
