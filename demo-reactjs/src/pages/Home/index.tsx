import React from 'react';
import { Outlet } from 'react-router-dom';

export const Home = () => {
	return (
		<div>
			<h1>Welcome</h1>

			<Outlet />
		</div>
	);
};

export default Home; 