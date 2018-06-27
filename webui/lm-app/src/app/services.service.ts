import {Component, Injectable,Input,Output,EventEmitter} from '@angular/core'


@Injectable()
export class SharedService {
  @Output() fire: EventEmitter<any> = new EventEmitter();
  //changeVar:boolean=!this.changeVar;
   constructor() {
     console.log('shared service started');
   }

   change(changeVar) {
    console.log('change started'); 
     this.fire.emit(!changeVar);
   }

   getEmittedValue() {
     return this.fire;
   }

} 