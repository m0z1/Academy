import { Col } from "react-bootstrap";
import TodoItem from "./TodoItem"

const TodoList = ({lists,deleteTodo}) => {
return (
    <div className="container">
        <h2>Todo List</h2>
        {
           lists &&  lists.map((todo,num) =>  (

                <TodoItem key={num} todo={todo} deleteTodo={deleteTodo} />
            
          
            ))
        }
    </div>
)

}

export default TodoList;