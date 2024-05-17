import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import InventoryService from '../../services/InventoryService';
import './Inventory.css';
import AdminNavbar from '../AdminNavbar';
 
function AddInventory() {
  const [inventory, setInventory] = useState({
    itemName: '',
    quantity: '',
    price: ''
  });
  const [errors, setErrors] = useState({});
 
  const handleChange = e => {
    const { name, value } = e.target;
    setInventory(prevInventory => ({
      ...prevInventory,
      [name]: value
    }));
    // Clear error message when user starts typing
    setErrors({ ...errors, [name]: '' });
  };
 
  const validateForm = () => {
    let valid = true;
    const newErrors = {};
 
    if (!inventory.itemName.trim()) {
      newErrors.itemName = 'Item Name is required';
      valid = false;
    }
    if (!inventory.quantity.trim()) {
      newErrors.quantity = 'Quantity is required';
      valid = false;
    }
    if (!inventory.price.trim()) {
      newErrors.price = 'Price is required';
      valid = false;
    }
    setErrors(newErrors);
    return valid;
  };
 
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;
 
    try {
      await InventoryService.addInventory(inventory);
      console.log('Inventory added successfully');
      window.location.href = '/inventory';
    } catch (error) {
      console.error('Error:', error);
    }
  };
 
  return (
    <>
    <AdminNavbar/>
   
    <div style={{
      width: "100%",
      height: "700px",
      backgroundImage:
      'url("https://img.freepik.com/free-photo/antique-books-rows-old-bookshelf-generated-by-ai_188544-16485.jpg?t=st=1714971907~exp=1714975507~hmac=a5b55fdf41466c50bc103f9378b2a6fee75d5466f24240dacfb963bedc3bdf8d&w=1060")',
      backgroundSize: "cover",
      backgroundRepeat: "no-repeat",
      backgroundPosition: "center",
      }}>
    <div className="form-container" style={{marginTop:"-20px"}}>
      <div className="form-content" style={{marginTop:"-30%"}}>
        <h2>Add Inventory</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="itemName">Item Name:</label>
            <input
              type="text"
              id="itemName"
              name="itemName"
              value={inventory.itemName}
              onChange={handleChange}
            />
            {errors.itemName && <p className="error">{errors.itemName}</p>}
          </div>
          <div>
            <label htmlFor="quantity">Quantity:</label>
            <input
              type="number"
              id="quantity"
              name="quantity"
              value={inventory.quantity}
              onChange={handleChange}
            />
            {errors.quantity && <p className="error">{errors.quantity}</p>}
          </div>
          <div>
            <label htmlFor="price">Price:</label>
            <input
              type="text"
              id="price"
              name="price"
              value={inventory.price}
              onChange={handleChange}
            />
            {errors.price && <p className="error">{errors.price}</p>}
          </div>
          <div className="button-group">
            <button type="submit">Add</button><br></br>
            <NavLink to="/inventory">Cancel</NavLink>
          </div>
        </form>
      </div>
    </div>
    </div>
    </>
  );
}
 
export default AddInventory;