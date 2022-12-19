import 'bootstrap/js/src/collapse.js';

import './styles.css';

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-md navbar-dark bg-primary main-nav">
      <div className="container-fluid">
        <a href="link" className="nav-logo-text">
          <h1>MovieFlix</h1>
        </a>
      </div>
    </nav>
  );
};

export default Navbar;
