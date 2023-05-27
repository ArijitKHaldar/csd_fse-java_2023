import React, { useEffect, useState } from "react";
import axios from "axios";
import {
  AreaChart,
  Area,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
} from "recharts";

function PredictSavings({ userId }) {
  const [predictedSavings, setPredictedSavings] = useState(null);
  const [monthlyData, setMonthlyData] = useState([]);

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

    const fetchMonthlyData = async () => {
      try {
        const currentDate = new Date();
        const currentYear = currentDate.getFullYear();
        const monthDataPromises = [];
        for (let i = 1; i <= 12; i++) {
          monthDataPromises.push(fetchMonthData(userId, currentYear, i));
        }
        const monthlyData = await Promise.all(monthDataPromises);
        setMonthlyData(monthlyData);
      } catch (error) {
        console.log("Error fetching monthly data:", error);
      }
    };

    fetchPredictedSavings();
    fetchMonthlyData();
  }, [userId]);

  const fetchMonthData = async (userId, year, month) => {
    try {
      const incomeResponse = await axios.get(
        `/api/income/v1/userid/${userId}/month/${month}`
      );
      const expenditureResponse = await axios.get(
        `/api/expenditure/v1/userid/${userId}/month/${month}`
      );
      const incomeAmount = incomeResponse.data.reduce(
        (total, income) => total + income.incomeAmount,
        0
      );
      const expenditureAmount = expenditureResponse.data.reduce(
        (total, expenditure) => total + expenditure.expenditureAmount,
        0
      );
      const savings = incomeAmount - expenditureAmount;
      return { month: month.toString(), savings };
    } catch (error) {
      console.log(`Error fetching data for month ${month}:`, error);
      return { month: month.toString(), savings: 0 };
    }
  };

  const getMonthString = (monthIndex) => {
    const monthNames = [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
    ];
    return monthNames[monthIndex - 1];
  };

  const getFullMonthString = (monthIndex) => {
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
  const currentFullMonth = getFullMonthString(new Date().getMonth() + 2);

  return (
    <div className="table-section">
      <div className="predict child-component income-section">
        <h2>Predicted Savings of {currentFullMonth}</h2>
        {predictedSavings !== null ? (
          <p>
            Based on the financial data you have entered, the predicted savings
            for the month of {currentMonth} is Rs. {predictedSavings}
          </p>
        ) : (
          <p>Could not predict savings</p>
        )}
        {monthlyData.length > 0 && (
          <div className="chart-container">
            <AreaChart
              width={1000}
              height={300}
              data={monthlyData}
              margin={{
                top: 20,
                right: 30,
                left: 20,
                bottom: 10,
              }}
            >
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis
                dataKey="month"
                tickFormatter={(value) => getMonthString(parseInt(value))}
              />
              <YAxis />
              <Tooltip />
              <Area
                type="monotone"
                dataKey="savings"
                fill="#8884d8"
                stroke="#8884d8"
              />
            </AreaChart>
          </div>
        )}
      </div>
    </div>
  );
}

export default PredictSavings;
