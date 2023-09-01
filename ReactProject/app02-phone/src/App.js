import { Component } from 'react';
import './App.css';
import PhoneForm from './component/PhoneForm';
import PhoneList from './component/PhoneList';

class App extends Component {
  id =2;

  state = {
    information : [
      {
        id : 0,
        name : "홍길동",
        phone : "010-1111-1111"
      },

      {
        id : 1,
        name : "이순신",
        phone : "010-2222-2222"
      }

    ]
}

//추가
handleCreate = (data) => {
  const {information} = this.state
  this.setState({
    information: information.concat({
      id: this.id++, ...data
    })
  })

} 

handleRemove=(id)=> {
  const {information} = this.state
  this.setState({
   information : information.filter(info => info.id !== id)
  })

}

render(){
  return (
    <div>
      <PhoneForm onCreate = {this.handleCreate}/>
      <PhoneList data={this.state.information}
      onRemove={this.handleRemove}/>
    </div>
  );

}
 
}

export default App;
