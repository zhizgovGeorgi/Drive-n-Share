import { Link, useNavigate } from 'react-router-dom';
import Logo from '../../src/images/logo-dns.png'
import AccountService from '../functions/AccountService';
import { useState, useEffect } from 'react';


export default function Navbar() {
  const navigate = useNavigate();
  const [buttons, setButtons] = useState(null);
  
  useEffect(()=>{
    const role = sessionStorage.getItem("role");

    if (role === "[Administrator]" ) {
     setButtons(<>
     <li> <button className='navbarbutton'><Link to={'/home'}><a>Home</a></Link></button> </li>
     <li> <button className='navbarbutton'><Link to={'/profile'}><a>Profile</a></Link></button> </li>
     <li> <button onClick={AccountService.logout} className='navbarbutton'><Link to={'/register'}><a>Log Out</a></Link></button> </li>
    
     </>);
     
    }
    else if (role === "[Driver]" ) {
      setButtons(<>
       
       <ul>
      <li> <button className='navbarbutton'><Link to={'/manageTravels'}><a>Manage Travels</a></Link></button> </li>
      <li> <button className='navbarbutton'><Link to={'/home'}><a>Home</a></Link></button> </li>
      <li> <button className='navbarbutton'><Link to={'/profile'}><a>Profile</a></Link></button> </li>
      <li> <button onClick={AccountService.logout} className='navbarbutton'><Link to={'/register'}><a>Log Out</a></Link></button> </li>
      </ul>
      </>);
      
     }
    else{
      setButtons(<>
      <ul>
      <li> <button className='navbarbutton'><Link to={'/register'}><a>Register</a></Link></button> </li>
      <li> <button className='navbarbutton'><Link to={'/login'}><a>Log in</a></Link></button> </li>
      </ul>
        </>);

    }
   
  },[])
  
  
    return (
      <div className="navbar">
        <div className="logonavbar">
        <img className="navbarlogo" src={Logo} />
        </div>
        <div className="navbarbuttons">
            {buttons}
      </div>
      </div>
    );
  }