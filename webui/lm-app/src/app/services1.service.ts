import {Component, Injectable,Input,Output,EventEmitter} from '@angular/core'


@Injectable()
export class SharedService1 {
  @Output() fire: EventEmitter<any> = new EventEmitter();
  //changeVar:boolean=!this.changeVar;
   constructor() {
     console.log('shared service started');
   }

   change1(changeVar1) {
    console.log('change started'); 
     this.fire.emit(!changeVar1);
   }

   

   getEmittedValue() {
     return this.fire;
   }

} 