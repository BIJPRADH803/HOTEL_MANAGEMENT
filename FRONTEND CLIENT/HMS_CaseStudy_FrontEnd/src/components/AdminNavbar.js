import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router";

function AdminNavbar() {
  const navigate = useNavigate();

  const adminLogout = () => {
    localStorage.removeItem("myUser");
    localStorage.clear();
    alert("You have successfully logged out as admin.");
    navigate("/");
  };

  return (
    <>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={""} className="navbar-brand">
          The Grand Hotel 
        </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/admin"} className="nav-link">
              Home
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/add-room"} className='nav-link'>
              Add Room
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/rooms"} className='nav-link'>
              View Rooms
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/add-staff"} className='nav-link'>
              Add Staff  
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/staff"} className='nav-link'>
             View Staff
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/add-booking"} className='nav-link'>
             Add Booking
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/bookings"} className='nav-link'>
             View Bookings
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/inventory"} className='nav-link'>
              Inventory Details
            </Link>
          </li>
        </div>
        <div className="ms-auto pe-md-5 navbar-nav">
          <li className='nav-item'>
            <Link to='/' onClick={adminLogout} className='nav-link'>
               Logout
            </Link>
          </li>
        </div>
      </nav>
    </>
  );
}

export default AdminNavbar;
