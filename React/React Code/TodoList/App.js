import logo from './logo.svg';
import './App.css';
import TodoList from './TodoList';
import { useEffect, useState } from 'react';
import axios from 'axios';
import TodoForm from './TodoForm';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [listContent, setlistContent] = useState([])
  useEffect(()=>{
    listTodo()
  },[])

  const listTodo = () => {
    axios.get("/api/list").then((resp)=>{
      setlistContent(resp.data)
    
    })
  }

  const deleteTodo = (num) => {
    axios.delete("/api/delete/"+num).then(()=>{
      alert('삭제 완료')
      setlistContent(listContent.filter(todo => todo.num !== num))
    })
  }



  const insertTodo = (data) => {
    console.log(data)
    axios.post("/api/insert",{
     subject : data.subject,
     name : data.name,
     summary : data.summary
    
    }).then((response) => {
      alert('등록완료입니다')
      setlistContent(listContent.concat({
        num : response.data.num,
        subject : data.subject,
        name : data.name,
        summary : data.summary,
        ...listContent
      }))
    })
  }

  const insertTodo2 = (todo) => {
    fetch('/api/insert',{
      method : 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        subject:todo.subject,
        name : todo.name,
        summary :todo.summary
      })
    })
    .then((resp) => resp.json())
    .then((resp) => {
      console.log(resp)
      alert('success')
      setlistContent(listContent.concat({
        num : resp.num,
        name:todo.name,
        subject:todo.subject,
        summary:todo.summary,
        ...listContent
      }))
    })

  }
  return (
    <div >
      <TodoForm listTodo ={listTodo} insertTodo2={insertTodo2} />
      <br/><hr/>
      <TodoList lists={listContent} deleteTodo={deleteTodo}/>
    </div>
  );
}

export default App;
