import React, { useEffect, useState } from "react";
import { Progress } from "reactstrap";
import { PieChart, Pie, Cell, Tooltip, Legend } from "recharts";
import axios from "axios";

function YearlySavingsPercentage({ userId }) {
  const [savingsPercentage, setSavingsPercentage] = useState(0);
  const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());

  useEffect(() => {
    axios
      .get(
        `/api/savings/calculation/v1/user/${userId}/savings/year/${selectedYear}`
      )
      .then((response) => {
        if (response.status === 200) {
          setSavingsPercentage(response.data);
        } else {
          setSavingsPercentage(0);
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          setSavingsPercentage(0);
        } else {
          console.log("Error fetching yearly savings percentage:", error);
        }
      });
  }, [userId, selectedYear]);

  const handleYearChange = (e) => {
    setSelectedYear(Number(e.target.value));
  };

  const COLORS = ["#5EA5DB", "#7FC2A4"];

  const pieChartData = [
    {
      id: "savings",
      name: `Savings ${savingsPercentage}%`,
      value: savingsPercentage,
    },
    {
      id: "expenditure",
      name: `Expenditure ${100 - savingsPercentage}%`,
      value: 100 - savingsPercentage,
    },
  ];

  return (
    <div className="table-section">
      <div className="yearly-savings-percentage child-component income-section">
        <h2>Yearly Savings Percentage</h2>
        <div className="yearly-dropdown">
          <label htmlFor="year">Select Year: </label>
          <select id="year" value={selectedYear} onChange={handleYearChange}>
            <option value={new Date().getFullYear() - 2}>
              {new Date().getFullYear() - 2}
            </option>
            <option value={new Date().getFullYear() - 1}>
              {new Date().getFullYear() - 1}
            </option>
            <option value={new Date().getFullYear()}>
              {new Date().getFullYear()}
            </option>
            <option value={new Date().getFullYear() + 1}>
              {new Date().getFullYear() + 1}
            </option>
            <option value={new Date().getFullYear() + 2}>
              {new Date().getFullYear() + 2}
            </option>
          </select>
        </div>
        <div className="progress-bar">
          <Progress
            value={savingsPercentage}
          >{`${savingsPercentage}%`}</Progress>
        </div>
        <div className="chart">
          <PieChart width={400} height={400}>
            <Pie
              data={pieChartData}
              cx="50%"
              cy="50%"
              innerRadius={100}
              outerRadius={170}
              fill="#8884d8"
              dataKey="value"
              startAngle={90}
              endAngle={-270}
              paddingAngle={1}
            >
              {pieChartData.map((entry) => (
                <Cell
                  key={entry.id}
                  fill={COLORS[pieChartData.indexOf(entry) % COLORS.length]}
                />
              ))}
            </Pie>
            <Legend />
            <Tooltip />
          </PieChart>
        </div>
      </div>
    </div>
  );
}

export default YearlySavingsPercentage;
