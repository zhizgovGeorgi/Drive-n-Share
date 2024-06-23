import { Link, useNavigate } from 'react-router-dom';
import Logo from '../../src/images/logo-dns.png'
import AccountService from '../functions/AccountService';
import { useState, useEffect } from 'react';


export default function Navbar() {
  const navigate = useNavigate();
  const [buttons, setButtons] = useState(null);

  const logout =()=>{
    AccountService.logout();
    navigate("/login")
  }
  
  useEffect(()=>{
    const role = sessionStorage.getItem("role");

    if (role === "Administrator" ) {
     setButtons(<>
     <li> <button className='navbarbutton'><Link to={'/home'}><a>Home</a></Link></button> </li>
     <li> <button className='navbarbutton'><Link to={'/profile'}><a>Profile</a></Link></button> </li>
     <li> <button onClick={AccountService.logout} className='navbarbutton'><Link to={'/register'}><a>Log Out</a></Link></button> </li>
    
     </>);
     
    }
    else if (role === "Driver" ) {
      setButtons(<>
       
       <ul>
      <li> <button className='navbarbutton'><Link to={'/home'}><a>Home</a></Link></button> </li>
      <li> <button className='navbarbutton'><Link to={'/profile'}><a>Profile</a></Link></button> </li>
      <li> <button className='navbarbutton'><Link to={'/manageTravels'}><a>Manage travels</a></Link></button> </li>
      <li> <button onClick={AccountService.logout} className='navbarbutton'><Link to={'/register'}><a>Log Out</a></Link></button> </li>
      </ul>
      </>);
      
     }
     else if (role === "Drivee" || role ==="Driver-to-be" ) {
      setButtons(<>
       
       <ul>
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