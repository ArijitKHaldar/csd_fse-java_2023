import React, { useEffect, useRef } from "react";
import "./PopupBox.css";

const PopupBox = ({ content, onClose }) => {
  const popupRef = useRef(null);

  useEffect(() => {
    const handleOutsideClick = (event) => {
      if (popupRef.current && !popupRef.current.contains(event.target)) {
        onClose();
      }
    };

    document.addEventListener("mousedown", handleOutsideClick);
    return () => {
      document.removeEventListener("mousedown", handleOutsideClick);
    };
  }, [onClose]);

  return (
    <div className="popup-box">
      <div className="popup-content" ref={popupRef}>
        <p>{content}</p>
      </div>
    </div>
  );
};

export default PopupBox;