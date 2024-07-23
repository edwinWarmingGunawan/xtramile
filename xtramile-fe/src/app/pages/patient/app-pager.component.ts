import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-pager',
  templateUrl: './app-pager.component.html',
  styleUrls: ['./app-pager.component.css']
})
export class PagerComponent implements OnInit {

  @Input() totalItems: number=10;
  @Input() currentPage: number=1;
  @Input() pageSize: number=10;

  @Output() pageChange = new EventEmitter<number>();

  totalPages: number=10;
  pages: number[] = [];

  ngOnInit(): void {
    this.totalPages = Math.ceil(this.totalItems / this.pageSize);
    this.generatePages();
  }

  generatePages(): void {
    this.pages = [];
    for (let i = 1; i <= this.totalPages; i++) {
      this.pages.push(i);
    }
  }

  onPageChange(page: number): void {
    this.pageChange.emit(page);
  }

  isFirstPage(): boolean {
    return this.currentPage === 1;
  }

  isLastPage(): boolean {
    return this.currentPage === this.totalPages;
  }
}