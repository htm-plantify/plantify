import axios from 'axios';
import React, { useEffect } from 'react';

import AdminTable from './components/AdminTable';

const headCells = [
  {
    id: 'id', label: 'ID',
  },
  {
    id: 'commonName', label: 'Common Name',
  },
  {
    id: 'scientificName', label: 'Scientific Name',
  },
  {
    id: 'price', label: 'Price',
  },
  {
    id: 'edit', label: '',
  },
  {
    id: 'delete', label: '',
  },
];

const App = () => {
  const [rows, setRows] = React.useState([]);

  useEffect(async () => {
    const result = await axios.get('http://localhost:8000/api/plants/');

    if (result.status === 200) setRows(result.data);
  }, []);

  return (
    <AdminTable rows={rows} headCells={headCells} />
  );
};

export default App;
