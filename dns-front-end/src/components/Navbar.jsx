import { Link } from 'react-router-dom';
import Logo from '../../src/images/logo-dns.png'
export default function Navbar() {
  
  
  
    return (
      <div className="navbar">
        <div className="logonavbar">
        <img className="navbarlogo" src={Logo} />
        </div>
        <div className="navbarbuttons">
            <ul>
      <li> <button><Link to={'/register'}><a>Register</a></Link></button> </li>
      <li><a>Hello 1</a></li>
      <li><a>Hello 1</a></li>
      </ul>
      </div>
      </div>
    );
  }