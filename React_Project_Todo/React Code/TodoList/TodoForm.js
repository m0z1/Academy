import { useState } from "react";
import { Button, Form, InputGroup } from "react-bootstrap";

const TodoForm = ({listTodo,insertTodo,insertTodo2}) => {
    const [content, setContent] = useState({
        subject : '',
        name : '',
        summary : ''
    })

    const getValue = (e) =>{
        setContent({
            ...content,
            [e.target.name] : e.target.value
        })
    }

const insertAll =() =>{
   insertTodo2(content)
    setContent({
        subject : '',
        name : '',
        summary : ''
    })

}


    return(
        <div className="container">
            <h1>To Do</h1>
            <b>subject</b><br/>
            <input type="text" name="subject" value={content.subject} onChange={getValue}/><br/><br/>
            <b>name</b><br/>
            <input type="text" name="name" value={content.name}  onChange={getValue} /><br/><br/>
            <b>summary</b><br/>
            <textarea type="text" name="summary" value={content.summary} onChange={getValue} cols="40" rows="10" /><br/>
            <Button variant="secondary" onClick={()=>insertAll()}>전송</Button>
        </div>
    )
}

export default TodoForm;