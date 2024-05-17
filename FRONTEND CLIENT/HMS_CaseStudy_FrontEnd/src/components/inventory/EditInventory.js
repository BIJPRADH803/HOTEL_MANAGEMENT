import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import InventoryService from '../../services/InventoryService';
import './Inventory.css';

const EditInventory = () => {
  const { id } = useParams();
  const [inventory, setInventory] = useState({
    itemName: '',
    quantity: '',
    price: ''
  });

  useEffect(() => {
    InventoryService.getInventoryById(id)
      .then(response => {
        setInventory(response.data);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }, [id]);

  const handleChange = event => {
    const { name, value } = event.target;
    setInventory(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = event => {
    event.preventDefault();
    InventoryService.updateInventory(id, inventory)
      .then(() => {
        console.log('Inventory updated successfully');
        window.alert('Inventory details updated successfully');
        window.location = '/inventory';
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
        <h2>Edit Inventory</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="itemName">Item Name:</label>
            <input
              type="text"
              id="itemName"
              name="itemName"
              value={inventory.itemName}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="quantity">Quantity:</label>
            <input
              type="number"
              id="quantity"
              name="quantity"
              value={inventory.quantity}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="price">Price:</label>
            <input
              type="number"
              id="price"
              name="price"
              value={inventory.price}
              onChange={handleChange}
            />
          </div>
          <div className="button-group">
            <button type="submit">Update</button><br></br>
            <Link to="/inventory" className="button">Back</Link>
          </div>
        </form>
      </div>
    </div>
    </div>
  );
};

export default EditInventory;
