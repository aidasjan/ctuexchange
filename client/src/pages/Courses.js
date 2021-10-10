import React, { useEffect, useState } from "react";
import { Container } from "reactstrap";
import Crud from "../components/Crud";

export default function Courses(props) {
  const [universities, setUniversities] = useState([]);

  useEffect(() => {
    fetch("/api/universities").then((response) =>
      response.json().then((results) => setUniversities(results))
    );
  }, []);

  return (
    <Container>
      <Crud
        url="/api/courses"
        tableColumns={["Id", "Code", "Name"]}
        getTableRowItems={(record) => [record.id, record.code, record.name]}
        formInputs={[
          { key: "code", label: "Code" },
          { key: "name", label: "Name" },
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
      />
    </Container>
  );
}
