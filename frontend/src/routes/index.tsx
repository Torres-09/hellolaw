import { BrowserRouter, Routes, Route } from 'react-router-dom';
import React from 'react';

import AuthCheck from '@pages/AuthCheck';
import Main from '@pages/Main';
import Home from '@pages/Home';
import ModalsProvider from '@components/Modal/manage/ModalsProvider';

const Router: React.FC = () => (
  <BrowserRouter>
    <Routes>
      <Route
        path="/"
        element={
          <ModalsProvider>
            <Main />
          </ModalsProvider>
        }
      />
      <Route path="/home" element={<Home />} />
      <Route path="/login" element={<AuthCheck />} />
    </Routes>
  </BrowserRouter>
);

export default Router;
