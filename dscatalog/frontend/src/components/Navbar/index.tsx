import './styles.scss';
import '@popperjs/core';
import 'bootstrap/js/src/collapse';

function Navbar() {
  return (
    <header className="navbar navbar-expand-md navbar-dark bg-primary main-nav">
      <div className="container-fluid">
        <a href="www.google.com.br" className="nav-logo-text">
          <h4>DS Catalog</h4>
        </a>

        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#dscatalog-navbar"
          aria-controls="dscatalog-navbar"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <nav className="collapse navbar-collapse" id="dscatalog-navbar">
          <ul className="navbar-nav offset-md-2 main-manu">
            <li>
              <a href="www.google.com.br" className="active">
                HOME
              </a>
            </li>
            <li>
              <a href="www.google.com.br">CATÁLOGO</a>
            </li>
            <li>
              <a href="www.google.com.br">ADMIN</a>
            </li>
          </ul>
        </nav>
      </div>
    </header>
  );
}

export default Navbar;
