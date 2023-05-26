import React, { useEffect, useState } from "react";
import { Progress } from "reactstrap";
import { PieChart, Pie, Cell, Tooltip, Legend } from "recharts";
import axios from "axios";
import "./MonthlySavingsPercentage.css";

function MonthlySavingsPercentage({ userId }) {
  const [savingsPercentage, setSavingsPercentage] = useState(0);
  const [selectedMonth, setSelectedMonth] = useState(new Date().getMonth() + 1);

  useEffect(() => {
    axios
      .get(
        `/api/savings/calculation/v1/user/${userId}/savings/month/${selectedMonth}`
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
          console.log("Error fetching monthly savings percentage:", error);
        }
      });
  }, [userId, selectedMonth]);

  const handleMonthChange = (e) => {
    setSelectedMonth(Number(e.target.value));
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
      <div className="monthly-savings-percentage child-component income-section">
        <h2>Monthly Savings Percentage</h2>
        <div className="month-dropdown">
          <label htmlFor="month">Select Month: </label>
          <select id="month" value={selectedMonth} onChange={handleMonthChange}>
            <option value={1}>January</option>
            <option value={2}>February</option>
            <option value={3}>March</option>
            <option value={4}>April</option>
            <option value={5}>May</option>
            <option value={6}>June</option>
            <option value={7}>July</option>
            <option value={8}>August</option>
            <option value={9}>September</option>
            <option value={10}>October</option>
            <option value={11}>November</option>
            <option value={12}>December</option>
          </select>
        </div>
        <div className="progress-bar">
          <Progress
            value={savingsPercentage}
          >{`${savingsPercentage}%`}</Progress>
        </div>
        <div className="pie-chart">
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

export default MonthlySavingsPercentage;
