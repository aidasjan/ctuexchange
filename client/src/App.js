import MainNavbar from './components/MainNavbar';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from 'react-router-dom';
import Students from './pages/Students';
import './App.css';
import Universities from './pages/Universities';
import Courses from './pages/Courses';
import Tags from './pages/Tags';

function App() {
  return (
    <div className="App">
      <MainNavbar />
      <Router>
        <Switch>
          <Route path="/students">
            <Students />
          </Route>
          <Route path="/universities">
            <Universities />
          </Route>
          <Route path="/courses">
            <Courses />
          </Route>
          <Route path="/tags">
            <Tags />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
