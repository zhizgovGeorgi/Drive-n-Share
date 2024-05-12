import Logo from '../../src/images/logo-dns.png'
export default function Navbar() {
  
  
  
    return (
      <div className="navbar">
        <div className="logonavbar">
        <img className="navbarlogo" src={Logo} />
        </div>
        <div className="navbarbuttons">
            <ul>
      <li><a>🏠Hello 1</a></li>
      <li><a>Hello 1</a></li>
      <li><a>Hello 1</a></li>
      </ul>
      </div>
      </div>
    );
  }