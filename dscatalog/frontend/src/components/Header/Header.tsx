import "./Header.scss";

export const Header = () => {
  return (
    <header className="navbar navbar-expand-lg navbar-dark m-md-5 p-md-3">
      <span className="container-fluid">
        <a className="navbar-brand" href="link">
          <h4>DS Catalog</h4>
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarDsCatalog"
          aria-controls="navbarDsCatalog"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <nav className="collapse navbar-collapse ms-md-5" id="navbarDsCatalog">
          <ul className="navbar-nav">
            <li className="nav-item">
              <a className="nav-link active" aria-current="page" href="link">
                HOME
              </a>
            </li>
            <li className="nav-item ms-lg-3">
              <a className="nav-link" href="link">
                CATÁLOGO
              </a>
            </li>
            <li className="nav-item ms-lg-3">
              <a className="nav-link" href="link">
                ADMIN
              </a>
            </li>
          </ul>
        </nav>
      </span>
    </header>
  );
};
