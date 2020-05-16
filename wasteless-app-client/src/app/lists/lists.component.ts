import { Component, OnInit, Input, ɵConsole } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, interval } from 'rxjs';
import { Lists } from '../lists';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { ListsService } from '../services/lists.service';
import { FormControl } from '@angular/forms';
import { UserService } from '../services/user.service';
import { User } from '../user';
import { ItemService } from '../services/item.service';
import { Item } from '../item';

@Component({
  selector: 'app-lists',
  templateUrl: './lists.component.html',
  styleUrls: ['./lists.component.css']
})
export class ListsComponent implements OnInit {

  title = "Wasteless App";
  public username: string;
  public password: string;

  constructor(private route: ActivatedRoute,
    private http: HttpClient,
    private service:ListsService,
    private itemService:ItemService,
    private userService:UserService,
    private _router: Router) { 
    this.route.params.subscribe( params => {this.username = params['username']; console.log("Hello from lists " + this.username)});
  }

  lists : Observable<Lists[]>;
  expiredItems : Observable<Item[]>;

  ngOnInit(): void {
    this.reloadData();
    this.reloadExpiredItems();

    this.reloadExpiredItems();
    interval(86400000).subscribe(
      (val) => { this.reloadExpiredItems() }
    ); //update every day -> not that glamorous but it works this way
  }

  reloadData(){
    this.lists = this.service.getLists(this.username);
  }

  reloadExpiredItems(){
    this.expiredItems = this.itemService.getExpiredItems(this.username);
  }

  editList(id:number){
    this._router.navigate(['/editList', this.username, id]);
  }

  deleteList(id:number){
    this.service.deleteList(id, this.username).subscribe(
      data => { this.reloadData(); }
    )
  }

  // newList(pageName:string): void{
  //   this._router.navigate([`${pageName}/${this.username}`]);
  // }

  calorieGoalForm = new FormControl('');
  user : User = new User();
  submitCalories(){
    this.user.username = this.username;
    this.user.goal = this.calorieGoalForm.value;
    this.user.password = this.password;
    
    this.userService.updateGoalForUser(this.user).subscribe();

    this._router.navigate(['/reports', this.username, this.user.goal]);
  } 

  newListNameForm = new FormControl('');
  newList : Lists = new Lists();
  submitNewList(){
    this.newList.id = 1;
    this.newList.name = this.newListNameForm.value;
    // console.log("LIST VALUE: " + this.newList.name);
    this.service.saveList(this.newList, this.username)
      .subscribe(data => {console.log(data), this.reloadData()}, error => console.log(error));
    this.newList = new Lists();
  }

  gotoLoginPage(pageName:string) :void {
    this._router.navigate([`${pageName}`]);
  }

  donateItem(itemId:number){
    this._router.navigate(['/charities', this.username, itemId]);
  }

}
