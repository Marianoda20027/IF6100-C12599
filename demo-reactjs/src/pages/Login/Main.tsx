import React from 'react';
import { Outlet } from 'react-router-dom';

export const Main = () => {
	return (
		<div>
			<h1>Home</h1>

			<Outlet />
		</div>
	);
};

export default Main; 