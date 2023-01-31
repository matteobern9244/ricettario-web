import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'DialogLoginAuth',
  templateUrl: './dialog-login-auth.component.html',
  styleUrls: ['./dialog-login-auth.component.css']
})
export class DialogLoginAuthComponent {

  username: string = "";
  password: string = "";

  show: boolean = false;

  submit() {
    console.log("user name is " + this.username)
    this.clear();
  }
  
  clear() {
    this.username = "";
    this.password = "";
    this.show = true;
  }

  constructor(
    public dialogRef: MatDialogRef<DialogLoginAuthComponent>
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}