import logo from './logo.svg';
import './App.css';
import Device from './Device';
import Counter from './Counter';
import Food from './Food';
import Movie from './Movie';
import Counter2 from './Counter2';
import MyDevice from './MyDevice';
import Mydevice1 from './MyDevice1';
import MyDevice2 from './MyDevice2';



function App() {

  const MydataApp = {
    "myDeviceData"  :[
        {
            name : "아이폰",
            ram : "6gb",
            touch : "yes",
            face : "yes"
        },
        {
            name : "갤럭시",
            ram : "12gb",
            touch : "yes",
            face : "yes"
        },
        {
            name : "샤오미",
            ram : "16gb",
            touch : "yes",
            face : "NO"
        },
        {
            name : "pixel",
            ram : "8gb",
            touch : "yes",
            face : "yes"
        }
    ]

  }


 return(
    <div>
      <hr />
      <h3>My Device Component</h3>
      <MyDevice mydata={MydataApp}/>

      <h3>My Device1  Component</h3>
      <Mydevice1 mydata={MydataApp.myDeviceData}/>

      <h3>My Device2  Component</h3>
      <MyDevice2 mydata={MydataApp.myDeviceData}/>

       <Movie title="영화1"/>
       <Movie title="영화2"/>
       <Movie title="영화3"/>
       <Movie title="영화4"/>
       <Counter2/>
      <h1>안녕</h1>
      <h2>안녕</h2>
      <Device/>
      <Counter/>
      <Food/>
     
    </div>
 );
}

export default App;

