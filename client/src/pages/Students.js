import React, { useEffect, useState } from "react";
import { Container } from "reactstrap";
import Crud from "../components/Crud";
import ManyToManySelector from "../components/ManyToManySelector";

export default function Students(props) {
  const [universities, setUniversities] = useState([]);
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    fetch("/api/universities").then((response) =>
      response.json().then((results) => setUniversities(results))
    );
    fetch("/api/courses").then((response) =>
      response.json().then((results) => setCourses(results))
    );
  }, []);

  return (
    <Container>
      <Crud
        url="/api/students"
        tableColumns={["Id", "Name", "Surname"]}
        getTableRowItems={(record) => [record.id, record.name, record.surname]}
        formInputs={[
          { key: "name", label: "Name" },
          { key: "surname", label: "Surname" },
          {
            key: "universityId",
            label: "University",
            options: universities.map((item) => ({
              value: item.id,
              label: item.name,
            })),
            findId: (record) => record.university.id,
          },
        ]}
        renderManyToManySelector={(record, handleRefresh) => (
          <ManyToManySelector
            buttonText="Edit Courses"
            postUrl={`/api/students/${record.id}/courses`}
            items={courses}
            selectedItems={record.courses}
            onChange={handleRefresh}
          />
        )}
      />
    </Container>
  );
}
