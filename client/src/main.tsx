import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { BrowserRouter, Route, Routes } from 'react-router'
import Homepage from './components/Homepage.tsx'
import { AuthProvider } from './components/contexts/AuthContext.tsx'
import { DataProvider } from './components/contexts/DataContext.tsx'
import Registration from './components/authentication/registration.tsx'
import Login from './components/authentication/Login.tsx'
import Card from './components/Card.tsx'
import ProtectedRoute from './components/protected/ProtectedRoute.tsx'
import NotFound from './components/protected/NotFound.tsx'


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
    <AuthProvider>
      <DataProvider>
    <Routes>
      <Route path='/' element={<App/>}>
      <Route index element={<Homepage/>}/>
      <Route path='/:entityID' element={<Card/>}/>
      <Route path='/*' element={<NotFound/>} />
      <Route element={<ProtectedRoute/>}>
      <Route path='/registration' element={<Registration/>}/>
      <Route path='/login' element={<Login/>}/>
      <Route path='/*' element={<NotFound/>} />
      </Route>
    </Route>
    </Routes>
    </DataProvider>
    </AuthProvider>
    </BrowserRouter>
  </StrictMode>,
)
