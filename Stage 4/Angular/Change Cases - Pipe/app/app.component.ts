import { Component } from '@angular/core';
//IMPORT STUDENT MODEL CLASS

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  //student : any; //Assign the Student details to the variable student which is type Student[].
  title = 'Student-db';
  student: any[] = [
      { name: 'Sam', registerNumber: 'RS200', age: 21 },
      { name: 'John', registerNumber: 'ST001', age: 22 },
      { name: 'Lilly', registerNumber: 'UV023', age: 20 },
    ];
}
