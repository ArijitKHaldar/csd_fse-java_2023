import { Component } from '@angular/core';
import { Student } from './Student.model';
//IMPORT STUDENT MODEL CLASS

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  //student:any ; //Assign the Student details to the variable student which is type Student[].
  student:Student[] = [ new Student('Sam', 'RS200', 21),
  new Student('John', 'ST001', 22),
  new Student('Lilly', 'UV023', 20)];
  title = 'Student-db';
}
