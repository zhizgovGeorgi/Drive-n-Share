import logo from './logo.svg';
import './App.css';
import Navbar from './components/Navbar';
import Home from './components/Home';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Register from './components/Register.jsx';
import Login from './components/Login.jsx';
import { ToastContainer, toast } from 'react-toastify';

function App() {
  return (
    <div className="App">
      <ToastContainer
position="top-right"
autoClose={5000}
hideProgressBar={false}
newestOnTop={false}
closeOnClick
rtl={false}
pauseOnFocusLoss
draggable
pauseOnHover
theme="dark"
/>
      <BrowserRouter>
      <Navbar/>
        <Routes>
        <Route path='/register' element={ <Register/>} />
        <Route path='/login' element={ <Login/>} />
          

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
