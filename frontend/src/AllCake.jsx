import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import InfiniteScroll from "react-infinite-scroll-component";
import Header from "./components/Header";
import UpDownContainer from "./components/layout/UpDownContainer";
import RowContainer from "./components/layout/RowContainer";
import GapH from "./components/layout/GapH";
import SmallMedium from "./components/text/SmallMedium";
import BoldSmallMedium from "./components/text/BoldSmallMedium";
import Card from "./components/Card";
import PinkSearch from "./assets/img/pink_search.png";
import { closePortfolio } from "./store/modalSlice";
import PortfolioModal from "./components/PortfolioModal";
import axios from "./util/axiosInstance";

function AllCake() {
  const [hasMoreItems, setHasMoreItems] = useState(true);
  const [pageNum, setPageNum] = useState(10);
  const modal = useSelector((state) => state.modal);
  const [popularCake, setPopularCake] = useState([]);
  // const [popularSeller, setPopularSeller] = useState([]);
  // const [orderOptions, setOrderOptions] = useState([]);
  const dispatch = useDispatch();
  const handleClickOutModal = () => {
    if (modal.portfolioOpen) {
      dispatch(closePortfolio());
    }
  };

  const fetchMoreData = async () => {
    try {
      const response = await axios.get(`/portfolio/list?page=${pageNum}`);
      const newData = response.data;

      if (newData.length === 0) {
        setHasMoreItems(false);
      } else {
        setPopularCake((prevData) => [...prevData, ...newData]);
        setPageNum((prevPageNum) => prevPageNum + 1);
      }
    } catch (error) {
      console.error("Failed to fetch more data:", error);
    }
  };

  return (
    <div>
      <Header handleClickOutModal={handleClickOutModal} />
      {modal.portfolioOpen ? <PortfolioModal /> : null}
      <UpDownContainer align="center" onClick={handleClickOutModal}>
        <GapH height="34px" />
        <RowContainer
          height="61px"
          width="451px"
          border="1px solid"
          borderRadius="30px"
          borderColor="#E3E3E3"
          position="relative"
        >
          <SmallMedium>내 지역 선택</SmallMedium>
          <img
            src={PinkSearch}
            alt="img"
            style={{
              width: "50px",
              position: "absolute",
              right: "26px",
            }}
          />
        </RowContainer>
        <GapH height="24px" />
        <RowContainer width="1194px" justify="start">
          <BoldSmallMedium>모든 케이크</BoldSmallMedium>
        </RowContainer>
        <GapH height="24px" />
        <InfiniteScroll
          dataLength={popularCake.length}
          next={fetchMoreData}
          hasMore={hasMoreItems}
          loader={<h4>Loading...</h4>}
          endMessage={
            <p style={{ textAlign: "center" }}>
              <b>목록의 마지막입니다.</b>
            </p>
          }
        >
          <div
            style={{
              display: "grid",
              gridTemplateColumns: "repeat(5, 1fr)",
              gridGap: "21px",
            }}
          >
            {popularCake.map((item) => {
              return (
                <Card
                  key={item.imageUrl[0]}
                  title={item.detail}
                  shape={item.shape}
                  sheetTaste={item.sheetTaste}
                  creamTaste={item.creamTaste}
                  situation={item.situation}
                  sellerId={item.businessName}
                  size={item.size}
                  detail={item.detail}
                  imgUrl={item.imageUrl[0]}
                />
              );
            })}
          </div>
        </InfiniteScroll>
      </UpDownContainer>
    </div>
  );
}

export default AllCake;