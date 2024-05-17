import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate hook
import RoomService from '../../services/RoomService';

function BookRoom() {
  const navigate = useNavigate(); // Initialize useNavigate hook

  const [searchCriteria, setSearchCriteria] = useState({
    checkInDate: '',
    checkOutDate: '',
    capacity: ''
  });

  const handleInputChange = event => {
    const { name, value } = event.target;
    setSearchCriteria({ ...searchCriteria, [name]: value });
  };

  const handleSearch = () => {
    // Redirect to search page with search criteria
    navigate(`/search?checkInDate=${searchCriteria.checkInDate}&checkOutDate=${searchCriteria.checkOutDate}&capacity=${searchCriteria.capacity}`);
  };

  return (
    <div  style={{
      width: "100%",
      height: "700px",
      backgroundImage:
      'url("https://img.freepik.com/free-photo/view-luxurious-hotel-interior-space_23-2150683453.jpg?t=st=1714307441~exp=1714311041~hmac=b47f02bfd4d423de47216b1fc24768cecf808be74fdd44c4a207b8ac0bbbe663&w=900")',
      backgroundSize: "cover",
      backgroundRepeat: "no-repeat",
      backgroundPosition: "center",
      marginTop: "-20px"
      }}>
    <div className="container">
      <h2>Book a Room</h2>
      <div className="form">
        <div className="form-group">
          <label htmlFor="checkInDate">Check-In Date:</label>
          <input
            type="date"
            className="form-control"
            id="checkInDate"
            required
            value={searchCriteria.checkInDate}
            onChange={handleInputChange}
            name="checkInDate"
          />
        </div>
        <div className="form-group">
          <label htmlFor="checkOutDate">Check-Out Date:</label>
          <input
            type="date"
            className="form-control"
            id="checkOutDate"
            required
            value={searchCriteria.checkOutDate}
            onChange={handleInputChange}
            name="checkOutDate"
          />
        </div>
        <div className="form-group">
          <label htmlFor="capacity">Capacity:</label>
          <input
            type="number"
            className="form-control"
            id="capacity"
            required
            value={searchCriteria.capacity}
            onChange={handleInputChange}
            name="capacity"
          />
        </div>
        <div className="button-group">
          <button onClick={handleSearch} className="button">Search</button>
        </div>
      </div>
    </div>
    </div>
  );
}

export default BookRoom;
