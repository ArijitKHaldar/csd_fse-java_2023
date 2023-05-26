import React, { useEffect, useState } from "react";
import axios from "axios";

function PredictSavings({ userId }) {
  const [predictedSavings, setPredictedSavings] = useState(null);

  useEffect(() => {
    const fetchPredictedSavings = async () => {
      try {
        const response = await axios.get(`/api/v1/predict/user/${userId}`);
        const predictedAmount = response.data;
        setPredictedSavings(predictedAmount);
      } catch (error) {
        console.log("Error fetching predicted savings:", error);
      }
    };

    fetchPredictedSavings();
  }, [userId]);

  const getMonthString = (monthIndex) => {
    const monthNames = [
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December",
    ];
    return monthNames[monthIndex - 1];
  };

  const currentMonth = getMonthString(new Date().getMonth() + 2);

  return (
    <div className="table-section">
      <div className="predict child-component income-section">
        <h2>Predicted Savings of {currentMonth}</h2>
        {predictedSavings !== null ? (
          <p>
            Based on the financial data you have entered, the predicted savings
            for the month of {currentMonth} is Rs. {predictedSavings}
          </p>
        ) : (
          <p>Could not predict savings</p>
        )}
      </div>
    </div>
  );
}

export default PredictSavings;
