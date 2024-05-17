// GetInventory.js
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import InventoryService from '../../services/InventoryService';

function GetInventory() {
  const { id } = useParams();
  const [inventory, setInventory] = useState({});

  useEffect(() => {
    InventoryService.getInventoryById(id)
      .then(response => {
        setInventory(response.data);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }, [id]);

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
    <div>
      <h2>Inventory Details</h2>
      <div>
        <strong>Item Name:</strong> {inventory.itemName}
      </div>
      <div>
        <strong>Quantity:</strong> {inventory.quantity}
      </div>
      <div>
        <strong>Price:</strong> {inventory.price}
      </div>
    </div>
    </div>
  );
}

export default GetInventory;
