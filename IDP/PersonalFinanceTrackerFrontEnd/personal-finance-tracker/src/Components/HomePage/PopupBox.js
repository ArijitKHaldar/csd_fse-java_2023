import React from "react";
import { FaTimes } from "react-icons/fa";
import "./PopupBox.css";

const PopupBox = ({ content, onClose }) => {
  return (
    <div className="popup-box">
      <div className="popup-content">
        <button className="close-icon" onClick={onClose}>
          <FaTimes className="close-icon-cross" />
        </button>
        <p>{content}</p>
      </div>
    </div>
  );
};

export default PopupBox;
