import React from 'react';
import { NavLink } from 'react-router-dom';

const Footer = () => {
  return (
    <footer>
      <div className="footer_area">
        <div className="footer_row">
          <div className="row d-flex justify-content-between">
            <div className="col-xl-3 col-lg-3 col-md-4 col-sm-6">
              <div className="single-footer-caption mb-30">
                <div className="footer_logo">
                  <NavLink className="footer_logo" to="/"><span>The<span className="foot_logo">Grand Hotel</span></span></NavLink>
                </div>
                <div className="footer_social">
                  <li><NavLink to="#"><i className="display-flex-center zmdi zmdi-facebook"></i></NavLink></li>
                  <li><NavLink to="#"><i className="display-flex-center zmdi zmdi-twitter"></i></NavLink></li>
                  <li><NavLink to="#"><i className="display-flex-center zmdi zmdi-google"></i></NavLink></li>
                </div>
                <div className="footer_para">
                  <p>Copyright ©|2024 All Rights Reserved</p>
                </div>
              </div>
            </div>
            <div className="col-xl-3 col-lg-3 col-md-3 col-sm-5">
              <div className="single-footer-caption mb-30">
                <div className="footer_title">
                  <h4>Quick Links</h4>
                  <ul>
                    <li><NavLink to="/"><b>About Grand  Hotel</b></NavLink></li>
                    <li><NavLink to="/"><b>Our Best Rooms</b></NavLink></li>
                    <li><NavLink to=""><b>Our Photo Gallery</b></NavLink></li>
                  </ul>
                </div>
              </div>
            </div>
            <div className="col-xl-3 col-lg-3 col-md-3 col-sm-3">
              <div className="single-footer-caption mb-30">
                <div className="footer_title">
                  <h4>Reservations</h4>
                  <ul>
                    <li><b>Tel:</b> +91 6372196327</li>
                    <li><b>Email:</b> thegrandhotel@gmail.com</li>
                  </ul>
                </div>
              </div>
            </div>
            <div className="col-xl-3 col-lg-3 col-md-4  col-sm-5">
              <div className="single-footer-caption mb-30">
                <div className="footer_title">
                  <h4>Our Location</h4>
                  <ul>
                    <li>Plot no - 89/B, Hinjewadi,Mumabi, India</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
