// RoomList.js
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import RoomService from '../../services/RoomService';
import './Room.css'; // Import CSS file
import AdminNavbar from '../AdminNavbar';

function RoomList() {
  const [rooms, setRooms] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchRooms();
  }, []);

  const fetchRooms = () => {
    RoomService.getAllRooms()
      .then(response => {
        setRooms(response.data);
      })
      .catch(error => {
        console.error('Error:', error);
        setError('An error occurred while fetching rooms');
      });
  };

  const handleDelete = id => {
    if (window.confirm('Are you sure you want to delete this room?')) {
      RoomService.deleteRoom(id)
        .then(() => {
          console.log('Room deleted successfully');
          fetchRooms();
        })
        .catch(error => {
          console.error('Error:', error);
        });
    }
  };

  return (
    <>
    <AdminNavbar/>
    
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
    <div className="container" style={{backgroundColor:"pink" , width:"80%"}}>
      <h2>Room List</h2>
      {error && <p>{error}</p>}
      <div className="table-container">
        <table className="content">
          <thead>
            <tr>
              <th>ID</th>
              {/* <th>Room Pic</th> */}
              <th>Room Number</th>
              <th>Room Type</th>
              <th>Room Service</th>
              <th>Price</th>
              <th >Actions</th>
            </tr>
          </thead>
          <tbody>
            {rooms.map(room => (
              <tr key={room.id}>
                <td>{room.id}</td>
                {/* <td>{room.roomPic}</td> */}
                <td>{room.roomNumber}</td>
                <td>{room.roomType}</td>
                <td>{room.roomService}</td>
                <td>{room.price}</td>
                <td c>
                  <Link to={`/edit-room/${room.id}`} className="button">Edit</Link>
                  <Link to={`/room/${room.id}`} className="button">View</Link>
                  <button onClick={() => handleDelete(room.id)} className="button">Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="button-group">
        <Link to="/admin" className="button">Dashboard</Link>
      </div>
    </div>
    </div>
    </>
  );
}

export default RoomList;
