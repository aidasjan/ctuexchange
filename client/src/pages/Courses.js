import React, { useEffect, useState } from "react";
import { Container } from "reactstrap";
import Crud from "../components/Crud";
import ManyToManySelector from "../components/ManyToManySelector";

export default function Courses(props) {
  const [universities, setUniversities] = useState([]);
  const [tags, setTags] = useState([]);

  useEffect(() => {
    fetch("/api/universities").then((response) =>
      response.json().then((results) => setUniversities(results))
    );
    fetch("/api/tags").then((response) =>
      response.json().then((results) => setTags(results))
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
        renderManyToManySelector={(record, handleRefresh) => (
          <ManyToManySelector
            buttonText="Edit Tags"
            postUrl={`/api/courses/${record.id}/tags`}
            items={tags}
            selectedItems={record.tags}
            onChange={handleRefresh}
          />
        )}
      />
    </Container>
  );
}
