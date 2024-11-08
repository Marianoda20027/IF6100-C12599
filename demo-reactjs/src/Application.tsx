import React, { useEffect, ReactNode } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import RegisterUser from './pages/RegisterUser';
import Login from './pages/Login/Login';
import Main from './pages/Login/Main';
import { useSessionHandler } from './hooks/useSessionHandler';

type CustomErrorBoundaryProps = {
  children: ReactNode;
};

const CustomErrorBoundary: React.FC<CustomErrorBoundaryProps> = ({ children }) => {
  return (
    <div>
      <h1>An unknown error occurred</h1>
      <p>Something went wrong, please contact an administrator.</p>
      {children}
    </div>
  );
};

const PublicRoute = () => {
  return (
    <Routes>
      <Route path="/" element={<Main />}>
        <Route index element={<Login />} />
        <Route path="register" element={<RegisterUser />} />
        <Route path="login" element={<Login />} />
      </Route>
    </Routes>
  );
};

const Application = () => {
  const { sessionContext, loadSessionFromToken } = useSessionHandler();

  useEffect(() => {
    if (sessionContext == null) {
      loadSessionFromToken();
    }
  }, [sessionContext, loadSessionFromToken]);

  return (
    <CustomErrorBoundary>
      <BrowserRouter>
        <PublicRoute />
      </BrowserRouter>
    </CustomErrorBoundary>
  );
};

export default Application;