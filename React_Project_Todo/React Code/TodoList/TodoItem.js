import { Button, Card } from "react-bootstrap";


const TodoItem = ({todo, deleteTodo}) => {
    return (
            <Card style={{ width: '18rem' }}>
      <Card.Body>
        <Card.Title>Todo</Card.Title>
        <Card.Text> 번호 : {todo.num}</Card.Text>
        <Card.Text> 주제 : {todo.subject}</Card.Text>
        <Card.Text> 이름 : {todo.name}</Card.Text>
        <Card.Text> 요약 : {todo.summary}</Card.Text>
        <Button variant="primary" onClick={()=> deleteTodo(todo.num)}> 삭제</Button>
      </Card.Body>
    </Card>
    )
}



export default TodoItem;