import React, { useState, useEffect } from 'react';
import { useParams, NavLink } from 'react-router-dom'; // Import useParams and NavLink
import StaffService from '../../services/StaffService';

function GetStaff() {
  const { id } = useParams();
  const [staff, setStaff] = useState({});

  useEffect(() => {
    getStaffById();
  }, []);

  const getStaffById = () => {
    StaffService.getStaffById(id)
      .then(response => {
        setStaff(response.data);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  };

  return (
    <div className="container">
      <h2>Staff Details</h2>
      <div className="content">
        <div>
          <label><strong>Name:</strong></label>{" "}
          <span>{staff.name}</span>
        </div>
        <div>
          <label><strong>Position:</strong></label>{" "}
          <span>{staff.position}</span>
        </div>
        <div>
          <label><strong>Salary:</strong></label>{" "}
          <span>{staff.salary}</span>
        </div>
        <div className="button-group">
          <NavLink to="/staff" className="button">Back</NavLink> {/* Back button using NavLink */}
        </div>
      </div>
    </div>
  );
}

export default GetStaff;
