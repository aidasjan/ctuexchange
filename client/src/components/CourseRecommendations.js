import React from "react";
import { useState } from "react/cjs/react.development";
import { Container, Table } from "reactstrap";
import { objectFetch } from "../api/ApiHelper";
import { ButtonModal } from "./ButtonModal";

export default function CourseRecommendations({ studentId, onChange }) {
  const [courseRecommendations, setCourseRecommendations] = useState(null);

  const handleClick = async () => {
    objectFetch(`/api/students/${studentId}/courseRecommendations`, "POST").then(
      (result) => { 
        setCourseRecommendations(result);
        onChange();
      }
    );
  };

  return (
    <ButtonModal text="Course Recommendations" className="btn btn-info">
      <Container className="text-center">
        <button onClick={handleClick} className="btn btn-primary mt-4 mb-4">
          Recommend Courses
        </button>
        {courseRecommendations ? (
          <Table>
            <thead>
              <th>Code</th>
              <th>Name</th>
              <th>Compatibility Score</th>
            </thead>
            <tbody>
              {courseRecommendations.map((recommendation) => (
                <tr>
                  <td>{recommendation.course.code}</td>
                  <td>{recommendation.course.name}</td>
                  <td>{recommendation.compatibilityScore}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : null}
      </Container>
    </ButtonModal>
  );
}
