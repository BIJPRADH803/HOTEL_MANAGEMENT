import React, { useState, useEffect } from 'react';
import { useParams, NavLink } from 'react-router-dom';
import RoomService from '../../services/RoomService';
import './Room.css';

const EditRoom = () => {
  const { id } = useParams();
  const [room, setRoom] = useState({
    roomNumber: '',
    roomType: '',
    roomService: '',
    price: ''
  });

  useEffect(() => {
    RoomService.getRoomById(id)
      .then(response => {
        setRoom(response.data);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }, [id]);

  const handleChange = event => {
    const { name, value } = event.target;
    setRoom(prevState => {
      console.log("Previous State:", prevState);
      return {
        ...prevState,
        [name]: value
      };
    });
  };

  const handleSubmit = event => {
    event.preventDefault();
    RoomService.updateRoom(id, room)
      .then(response => {
        console.log('Room updated successfully:', response.data);
        // Redirect to the list of rooms using NavLink
        window.location.href = '/rooms';
      })
      .catch(error => {
        console.error('Error:', error);
      });
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
    <div className="form-container">
      <div className="form-content">
        <h2>Edit Room</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="roomNumber">Room Number:</label>
            <input
              type="text"
              id="roomNumber"
              name="roomNumber"
              value={room.roomNumber}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="roomType">Room Type:</label>
            <input
              type="text"
              id="roomType"
              name="roomType"
              value={room.roomType}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="roomService">Room Service:</label>
            <input
              type="text"
              id="roomService"
              name="roomService"
              value={room.roomService}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="price">Price:</label>
            <input
              type="number"
              id="price"
              name="price"
              value={room.price}
              onChange={handleChange}
            />
          </div>
          <div className="button-group">
            <button type="submit">Update</button>
            <NavLink to="/rooms">Back</NavLink>
          </div>
        </form>
      </div>
    </div>
    </div>
  );
};

export default EditRoom;
