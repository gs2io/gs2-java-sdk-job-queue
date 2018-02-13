/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package io.gs2.jobQueue.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ジョブ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class JobResult implements Serializable {

	/** ジョブID */
	private String jobId;

	/** キューGRN */
	private String queueId;

	/** ステータスコード */
	private Integer statusCode;

	/** 実行結果 */
	private String result;

	/** キューの中で最後のジョブだったか */
	private Boolean endOfJob;

	/** 作成日時 */
	private Integer createAt;


	/**
	 * ジョブIDを取得
	 *
	 * @return ジョブID
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * ジョブIDを設定
	 *
	 * @param jobId ジョブID
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * キューGRNを取得
	 *
	 * @return キューGRN
	 */
	public String getQueueId() {
		return queueId;
	}

	/**
	 * キューGRNを設定
	 *
	 * @param queueId キューGRN
	 */
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}

	/**
	 * ステータスコードを取得
	 *
	 * @return ステータスコード
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * ステータスコードを設定
	 *
	 * @param statusCode ステータスコード
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * 実行結果を取得
	 *
	 * @return 実行結果
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 実行結果を設定
	 *
	 * @param result 実行結果
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * キューの中で最後のジョブだったかを取得
	 *
	 * @return キューの中で最後のジョブだったか
	 */
	public Boolean getEndOfJob() {
		return endOfJob;
	}

	/**
	 * キューの中で最後のジョブだったかを設定
	 *
	 * @param endOfJob キューの中で最後のジョブだったか
	 */
	public void setEndOfJob(Boolean endOfJob) {
		this.endOfJob = endOfJob;
	}

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createAt 作成日時
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

}