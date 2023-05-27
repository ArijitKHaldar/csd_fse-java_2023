import React, { useState, useEffect } from "react";
import { Button, Form, FormGroup, Input, Label, Progress } from "reactstrap";
import axios from "axios";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";

function FinancialGoal({ userId }) {
  const [date, setDate] = useState("");
  const [amount, setAmount] = useState("");
  const [percentageCompletion, setPercentageCompletion] = useState(null);
  const [error, setError] = useState(null);
  const [expenditureData, setExpenditureData] = useState([]);
  const [categoryData, setCategoryData] = useState([]);
  const [occurrencesData, setOccurrencesData] = useState([]);

  useEffect(() => {
    axios
      .get(`/api/expenditure/v1/userid/${userId}`)
      .then((response) => {
        setExpenditureData(response.data);
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          setExpenditureData([]);
        } else {
          console.log("Error fetching expenditure data:", error);
        }
      });

    axios
      .get(`/api/category/v1/view`)
      .then((response) => {
        setCategoryData(response.data);
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          setCategoryData([]);
        } else {
          console.log("Error fetching income data:", error);
        }
      });
  }, [userId]);

  useEffect(() => {
    const getOccurrencesByMonthAndCategory = () => {
      const occurrencesByMonthAndCategory = {};

      expenditureData.forEach((expenditure) => {
        const month = new Date(expenditure.expenditureDate).toLocaleString(
          "en-US",
          { month: "short" }
        );
        const categoryId = expenditure.categoryId;

        if (!occurrencesByMonthAndCategory[month]) {
          occurrencesByMonthAndCategory[month] = {};
        }

        if (!occurrencesByMonthAndCategory[month][categoryId]) {
          occurrencesByMonthAndCategory[month][categoryId] = {
            occurrences: 0,
            categoryId: categoryId,
          };
        }

        occurrencesByMonthAndCategory[month][categoryId].occurrences++;
      });

      return Object.keys(occurrencesByMonthAndCategory).map((month) => {
        const categoryOccurrences = occurrencesByMonthAndCategory[month];
        const maxOccurrences = Math.max(
          ...Object.values(categoryOccurrences).map(
            (category) => category.occurrences
          )
        );
        const categoryId = Object.keys(categoryOccurrences).find(
          (categoryId) =>
            categoryOccurrences[categoryId].occurrences === maxOccurrences
        );
        return {
          month,
          "Frequent Expenditure Category": maxOccurrences,
          categoryId,
        };
      });
    };

    const occurrencesData = getOccurrencesByMonthAndCategory();
    setOccurrencesData(occurrencesData);
  }, [categoryData, expenditureData]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const currentDate = new Date();
    const selectedDate = new Date(date);

    if (selectedDate <= currentDate) {
      setError("Please input a future date");
      return;
    }

    try {
      const response = await axios.get(
        `/api/v1/goal/user/${userId}/date/${date}/amount/${amount}`
      );
      const completionPercentage = response.data;
      setPercentageCompletion(completionPercentage);
      setError(null);
    } catch (error) {
      setError("Data Inconsistencies Found. Cannot calculate");
      console.log("Error calculating savings completion:", error);
    }
  };

  const getCategoryTag = (categoryId) => {
    if (categoryData.length === 0) {
      return "Loading...";
    }
    const category = categoryData.find(
      (category) => category.categoryId === categoryId
    );
    return category ? category.expenditureTag : "No Data";
  };

  const customTooltip = ({ active, payload }) => {
    if (active && payload && payload.length) {
      const categoryId = payload[0].payload.categoryId;
      return (
        <div className="custom-tooltip">
          <p className="label">{`Category: ${getCategoryTag(
            parseInt(categoryId)
          )}`}</p>
        </div>
      );
    }

    return null;
  };

  return (
    <div className="table-section">
      <div className="goal child-component income-section">
        <h2>Financial Goal</h2>
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="date">Date:</Label>
            <Input
              type="date"
              id="date"
              value={date}
              onChange={(e) => setDate(e.target.value)}
              required
            />
          </FormGroup>
          <FormGroup>
            <Label for="amount">Amount:</Label>
            <Input
              type="number"
              id="amount"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              required
            />
          </FormGroup>
          <Button type="submit" color="primary">
            Calculate
          </Button>
        </Form>
        {error ? (
          <div className="mt-4">
            <h4>{error}</h4>
          </div>
        ) : (
          percentageCompletion !== null && (
            <div className="mt-4">
              <h4>Savings Progress: {percentageCompletion}%</h4>
              <Progress value={percentageCompletion} />
            </div>
          )
        )}

        <div className="bar-chart">
          <BarChart
            width={600}
            height={250}
            data={occurrencesData}
            margin={{ top: 20, right: 30, left: 20, bottom: 5 }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="month" />
            <YAxis />
            <Tooltip content={customTooltip} />
            <Legend />
            <Bar dataKey="Frequent Expenditure Category" fill="#8884d8" />
          </BarChart>
        </div>
      </div>
    </div>
  );
}

export default FinancialGoal;
