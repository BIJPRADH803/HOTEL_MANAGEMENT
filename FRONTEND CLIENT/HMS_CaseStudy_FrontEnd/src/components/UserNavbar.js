import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router";

function UserNavbar() {
  const myUser = JSON.parse(localStorage.getItem("myUser"));
  const navigate = useNavigate();

  const userLogout = () => {
    if (myUser != null) {
      localStorage.removeItem("myUser");
      localStorage.clear();
      alert("You have successfully logged out.");
      navigate("/");
    } else {
      alert("You have successfully logged out . ");
    }
  };

  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
    } else {
      setButton(true);
    }
  };

  useEffect(() => {
    showButton();
  }, []);

  window.addEventListener("resize", showButton);

  return (
    <>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={""} className="navbar-brand">
          The Grand Hotel
        </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={""} className="nav-link">
              Home
            </Link>
          </li>
        </div>

        <div className="ms-auto pe-md-5 navbar-nav">
          <li className="nav-item">
            <Link to="/" onClick={userLogout} className="nav-link">
              LogOut
            </Link>
          </li>
        </div>
      </nav>
    </>
  );
}
export default UserNavbar;
