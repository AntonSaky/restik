import './App.css';
import { AdminPanel } from './Components/AdminPanel/AdminPanel';
import { BasketPage } from './Components/BasketPage/BasketPage';
import { SuccessOrder } from './Components/BasketPage/SuccessOrder/SuccessOrder';
import { Footer } from './Components/Footer/Footer';
import { LoginPage } from './Components/LoginPage/LoginPage';
import { MainPage } from './Components/MainPage/MainPage';
import { Navbar } from './Components/Navbar/Navbar';
import { RegistrationPage } from './Components/RegistrationPage/RegistrationPage';
import { SingleCategoryPage } from './Components/SingleCategoryPage/SingleCategoryPage';
import {Route, Routes} from 'react-router-dom';

function App() {
  return (
    <>
      {/* <Navbar /> */}
      <Routes>
        <Route path='/' element={<MainPage/>}/>
        <Route path='/single-category/:category_id' element={<SingleCategoryPage />}/>
        <Route path='/basket' element={<BasketPage />}/>
        <Route path='/login' element={<LoginPage />}/>
        <Route path='/registration' element={<RegistrationPage />}/>
        <Route path='/admin' element={<AdminPanel />}/>
        <Route path='/success' element={<SuccessOrder />}/>
      </Routes>
      {/* <Footer /> */}
    </>
  );
}

export default App;
