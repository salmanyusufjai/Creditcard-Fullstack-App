import './App.css';

import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import CreateCreditcardComponent from  './components/CreateCreditcardComponent';
function App() {
  return (
    <div className="App">
          <div className='container'>

            <Router basename="/creditcard-ui">
              <Routes>
                <Route path = "/" element={<CreateCreditcardComponent/>}></Route>
              </Routes>
            </Router>
          </div>
    </div>
  );
}

export default App;
